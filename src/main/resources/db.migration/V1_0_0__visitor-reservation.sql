CREATE SCHEMA IF NOT EXISTS "public";

-- public.visitor definition
CREATE TABLE public.visitor (
    id UUID NOT NULL,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(128) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    created_by UUID NULL,
    updated_by UUID NULL,
    created_by_ip_address VARCHAR(15) NULL,
    updated_by_ip_address VARCHAR(15) NULL,
    created_date TIMESTAMP NULL,
    updated_date TIMESTAMP NULL,
    CONSTRAINT visitor_pk PRIMARY KEY (id)
);

-- public.reservation definition
CREATE TABLE public.reservation (
    id UUID NOT NULL,
    visitor_id UUID NOT NULL,
    reservation_date TIMESTAMP NOT NULL,
    created_by UUID NULL,
    updated_by UUID NULL,
    created_by_ip_address VARCHAR(15) NULL,
    updated_by_ip_address VARCHAR(15) NULL,
    created_date TIMESTAMP NULL,
    updated_date TIMESTAMP NULL,
    CONSTRAINT reservation_pk PRIMARY KEY (id),
    CONSTRAINT visitor_reservation_fk FOREIGN KEY (visitor_id) REFERENCES public.visitor (id) ON DELETE CASCADE ON UPDATE CASCADE
);
