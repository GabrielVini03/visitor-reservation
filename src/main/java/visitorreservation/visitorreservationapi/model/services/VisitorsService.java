package visitorreservation.visitorreservationapi.model.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.reservation.ReservationDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.CreateVisitorRequestDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.visitor.VisitorDTO;
import visitorreservation.visitorreservationapi.model.entities.Visitor;
import visitorreservation.visitorreservationapi.model.mappers.ReservationsMapper;
import visitorreservation.visitorreservationapi.model.mappers.VisitorsMapper;
import visitorreservation.visitorreservationapi.model.repositories.VisitorsRepository;

import javax.validation.ValidationException;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitorsService {

    VisitorsRepository visitorsRepository;

    VisitorsMapper visitorsMapper;

    ReservationsMapper reservationsMapper;

    @Autowired
    public void VisitorsService(VisitorsRepository visitorsRepository, VisitorsMapper visitorsMapper, ReservationsMapper reservationsMapper){
        this.visitorsMapper = visitorsMapper;
        this.visitorsRepository = visitorsRepository;
        this.reservationsMapper = reservationsMapper;
    }

    public Visitor findByVisitor(UUID visitorId){
        Optional<Visitor> visitor = visitorsRepository.findById(visitorId);

        if (visitor.isEmpty()) throw new DataNotFoundException();

        return visitor.get();
    }
    public VisitorDTO insert(CreateVisitorRequestDTO createVisitorRequestDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {

        VisitorDTO visitorDTO = VisitorDTO.builder()
                .name(createVisitorRequestDTO.getName())
                .phone(createVisitorRequestDTO.getPhone())
                .email(createVisitorRequestDTO.getEmail())
                .build();

        if (visitorsRepository.existsByEmail(visitorDTO.getEmail())) {
            throw new DataNotFoundException("There is already a visitor with the provided email.");
        }

        if (visitorsRepository.existsByPhone(visitorDTO.getPhone())) {
            throw new DataNotFoundException("There is already a visitor with the provided phone number.");
        }

        Visitor visitor = visitorsRepository.saveAndFlush(visitorsMapper.mapFromVisitorDTO(visitorDTO));
        return visitorsMapper.mapFromVisitor(visitor);
    }

    public VisitorDTO update(UpdateVisitorDTO updateVisitorDTO, UUID visitorId) throws DataNotFoundException{

        Visitor visitor = this.findByVisitor(visitorId);

        VisitorDTO visitorDTO = VisitorDTO.builder()
                .email(updateVisitorDTO.getEmail())
                .name(updateVisitorDTO.getName())
                .phone(updateVisitorDTO.getPhone())
                .build();

        if (!visitor.getEmail().equals(visitorDTO.getEmail()) && visitorsRepository.existsByEmail(visitorDTO.getEmail())) {
            throw new DataNotFoundException("There is already a visitor with the provided email.");
        }

        if (!visitor.getPhone().equals(visitorDTO.getPhone()) && visitorsRepository.existsByPhone(visitorDTO.getPhone())) {
            throw new DataNotFoundException("There is already a visitor with the provided phone number.");
        }

        visitorsMapper.updateVisitorFromVisitorDTO(visitorDTO, visitor);

        return visitorsMapper.mapFromVisitor(visitorsRepository.saveAndFlush(visitor));

    }

    public VisitorDTO find(UUID visitorId) throws DataNotFoundException {
        return visitorsMapper.mapFromVisitor(this.findByVisitor(visitorId));
    }

    public Collection<VisitorDTO> list(){
        return visitorsMapper.mapFromVisitorCollection(visitorsRepository.findAll());
    }

    public boolean delete(UUID visitorId) throws DataNotFoundException {
      Visitor visitor = this.findByVisitor(visitorId);

      visitorsRepository.delete(visitor);

      return true;
    }

}


