create table t_pictures(
    picture_id varchar primary key,
    picture text,
    file_name varchar,
    file_type varchar,
    file bytea,
    journal_id varchar,
    created_at timestamp ,
    created_by varchar,
    updated_at timestamp ,
    updated_by varchar,
    is_deleted bool,
    deleted_at timestamp ,
    deleted_by varchar,
    constraint fk_journal_id FOREIGN key (journal_id) references t_journals(journal_id) on delete  cascade
)