create table users
(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(500) not null,
    enabled  boolean not null
);
create table authorities
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_auth_username on authorities (username, authority);


-- özelleştirilmiş custom table
CREATE TABLE customer
(
    id    int          NOT Null AUTO_INCREMENT,
    email varchar(50)  NOT Null,
    pwd   varchar(200) NOT Null,
    role  varchar(45)  NOT Null,
    PRIMARY KEY (id)
)

INSERT INTO customer ('email','pwd','role') values('happy@example.com','{noop}User12345*#','read');
INSERT INTO customer  ('email','pwd','role') values('admin@example.com','{bcrypt}$2a$12$vMoqydV5ka8M.3NUTMMP0.qsSWtHvVLOWagPTZERE.i/8FSimpglS','admin')