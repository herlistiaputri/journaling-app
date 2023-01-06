Create table t_user
(
    user_id varchar primary key,
    name varchar,
    email varchar,
    password text,
    pin text,
    is_active bool,
    access_token text,
    refresh_token text,
    token_id varchar,
    last_online timestamp,
    created_at timestamp,
    created_by varchar,
    updated_at timestamp,
    updated_by varchar,
    is_deleted bool,
    deleted_at timestamp,
    deleted_by varchar
);