Create table t_journals(
    journal_id varchar primary key,
    title varchar,
    content text,
    created_at timestamp ,
    created_by varchar,
    updated_at timestamp ,
    updated_by varchar,
    is_deleted bool,
    deleted_at timestamp ,
    deleted_by varchar
);