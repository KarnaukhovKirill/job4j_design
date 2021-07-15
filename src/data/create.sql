create table roles (
	id serial primary key,
	title varchar(30)
);
create table category (
	id serial primary key,
	name varchar(255)
);
create table rules (
	id serial primary key,
	name varchar(255)
);
create table states (
	id serial primary key,
	status varchar(30)
);
create table users (
	id serial primary key,
	name varchar(255),
	age int,
	role_id int references roles(id)
);
create table role_rules (
	id serial primary key,
	role_id int references roles(id),
	rule_id int references rules(id)
);
create table item (
	id serial primary key,
	user_id int references users(id),
	category_id int references category(id),
	state_id int references states(id)
);
create table comments (
	id serial primary key,
	commentary text,
	item_id int references item(id)
);
create table attachs (
	id serial primary key,
	url varchar(255),
	item_id int references item(id)
);