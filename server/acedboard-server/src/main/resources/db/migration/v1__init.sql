create table users(
    id bigserial primary key,
    email text unique not null,
    password_hash text not null,
    created_at timestamp with time zone default current_timestamp not null
);

create table projects(
    id bigserial primary key,
    name text not null,
    description text,
    owner_id bigint references users(id) on delete cascade,
    created_at timestamp with time zone default current_timestamp not null
);

create table tasks(
    id bigserial primary key,
    title text not null,
    description text,
    status text default 'todo' not null,
    priority text default 'medium' not null,
    due_at timestamp with time zone,
    estimate_minutes integer,
    created_at timestamptz default now(),
    updated_at timestamptz default now()

);