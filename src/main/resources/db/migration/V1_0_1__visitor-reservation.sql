-- Vn__Create_Visitor_Reservation_Table.sql

CREATE TABLE public.visitor_reservation (
    id UUID NOT NULL,
    visitor_id UUID NOT NULL,
    reservation_id UUID NOT NULL,
    created_by UUID NULL,
    updated_by UUID NULL,
    created_by_ip_address VARCHAR(15) NULL,
    updated_by_ip_address VARCHAR(15) NULL,
    created_date TIMESTAMP NULL,
    updated_date TIMESTAMP NULL,
    CONSTRAINT visitor_reservation_pk PRIMARY KEY (id),
    CONSTRAINT visitor_reservation_visitor_fk FOREIGN KEY (visitor_id) REFERENCES public.visitor (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT visitor_reservation_reservation_fk FOREIGN KEY (reservation_id) REFERENCES public.reservation (id) ON DELETE CASCADE ON UPDATE CASCADE
);
