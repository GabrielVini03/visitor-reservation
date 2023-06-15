package visitorreservation.visitorreservationapi.model.services;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visitorreservation.visitorreservationapi.commons.exceptions.DataNotFoundException;
import visitorreservation.visitorreservationapi.controller.DTO.domains.UpdateVisitorDTO;
import visitorreservation.visitorreservationapi.controller.DTO.domains.VisitorDTO;
import visitorreservation.visitorreservationapi.model.entities.Visitor;
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

    @Autowired
    public void VisitorsService(VisitorsRepository visitorsRepository, VisitorsMapper visitorsMapper){
        this.visitorsMapper = visitorsMapper;
        this.visitorsRepository = visitorsRepository;
    }

    public Visitor findByVisitor(UUID visitorId){
        Optional<Visitor> visitor = visitorsRepository.findById(visitorId);

        if (visitor.isEmpty()) throw new DataNotFoundException();

        return visitor.get();
    }
    public VisitorDTO insert(VisitorDTO visitorDTO) throws ConstraintViolationException, DataNotFoundException, ValidationException {
        Visitor visitor = visitorsRepository.saveAndFlush(visitorsMapper.mapFromVisitorDTO(visitorDTO));

        return visitorsMapper.mapFromVisitor(visitor);
    }

    public VisitorDTO update(UpdateVisitorDTO UpdateVisitorDTO, UUID visitorId) throws DataNotFoundException{

        Visitor visitor = this.findByVisitor(visitorId);

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


