CREATE TABLE categories (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(100) NOT NULL UNIQUE,

    description VARCHAR(500),

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL

);



CREATE TABLE techs (

    id BIGSERIAL PRIMARY KEY,

    name VARCHAR(120) NOT NULL UNIQUE,

    description VARCHAR(500),

    category_id BIGINT NOT NULL,

    created_at TIMESTAMP NOT NULL,

    updated_at TIMESTAMP NOT NULL,


    CONSTRAINT fk_tech_category

        FOREIGN KEY (category_id)

        REFERENCES categories(id)

);