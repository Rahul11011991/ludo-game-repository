---------------------------------------------------------------------------------------

drop sequence if exists public.game_seq cascade;

CREATE SEQUENCE public.game_seq
start with 1
increment by 1
minvalue 0
maxvalue 100000
cycle;

drop table if exists public.game cascade;

create table public.game  (
	game_id integer NOT NULL DEFAULT (nextval('public.game_seq')::regclass) ,
	start_time timestamp without time zone ,
	end_time timestamp without time zone ,
	status varchar(30) , 
	no_of_players integer ,
	winner_participant_id integer,
	created_by varchar(50),
  	created_date timestamp without time zone ,
  	constraint game_pk primary key (game_id) 
);

------------------------------------------------------------------------------------------

drop  sequence if exists public.player_seq CASCADE;

CREATE SEQUENCE public.player_seq
start with 1
increment by 1
minvalue 0
maxvalue 100000
cycle;

DROP TABLE IF EXISTS public.player cascade;

create table public.player (
  player_id integer NOT NULL DEFAULT (nextval('public.player_seq')::regclass) ,
  first_name varchar(50),
  last_name varchar(50),
  current_age int ,
  gender boolean,
  created_by varchar(50),
  created_date timestamp without time zone , 
  constraint player_pk primary key (player_id)
);


---------------------------------------------------------------------------------------------

drop sequence if exists public.participant_seq cascade;

CREATE SEQUENCE public.participant_seq
start with 1
increment by 1
minvalue 0
maxvalue 100000
cycle;

drop table if exists public.participant cascade;

create table public.participant  (
	participant_id integer NOT NULL DEFAULT (nextval('public.participant_seq')::regclass) ,
	player_id integer ,
	game_id integer ,
	status varchar(30) , 
	created_by varchar(50),
  	created_date timestamp without time zone ,
  	constraint participant_pk primary key (participant_id) ,
  	constraint player_fk foreign key (player_id)
  	references public.player(player_id) match simple 
  	on update no action  ,
  	constraint game_fk foreign key (game_id)
  	references public.game(game_id) match simple 
  	on update no action 
);

-------------------------------------------------------------------------------------------

drop table if exists public.step_master cascade;

create table public.step_master  (
	step_id integer NOT NULL  ,
	step_no integer ,
  	constraint step_pk primary key (step_id)
);

------------------------------------------------------------------------------------------

drop table if exists public.event_master cascade;

create table public.event_master  (
	event_id integer NOT NULL  ,
	event_name varchar(200),
	event_operation integer, -- plus , substract , equals to zero back to home
	event_value integer ,  -- 1  to 6 values would be there
	event_description varchar(200),
	created_by varchar(50),
	created_date timestamp without time zone ,
  	constraint event_pk primary key (event_id)
);

------------------------------------------------------------------------------------------

drop table if exists public.token_master cascade;

create table public.token_master  (
	token_id integer NOT NULL  , 
	token_no integer ,  -- token value from 1 to 4
  	constraint token_pk primary key (token_id)
);

-----------------------------------------------------------------------------------------

drop table if exists public.configuration_master cascade;

create table public.configuration_master  (
	configuration_id integer NOT NULL  ,
	configuration_key varchar(50) ,  -- SAFE_ZONE , LAST_STEP   
  	configuration_value varchar(50) ,   
	constraint configuration_pk primary key (configuration_id)
);

-------------------------------------------------------------------------------------------

drop sequence if exists public.move_seq cascade;

CREATE SEQUENCE public.move_seq
start with 1
increment by 1
minvalue 0
maxvalue 100000
cycle;


drop table if exists public.move cascade;

create table public.move  (
	move_id integer NOT NULL DEFAULT (nextval('public.move_seq')::regclass) ,
	participant_id integer ,
	game_id integer ,
	step_id integer ,
	token_id integer ,
	status varchar(50) ,
	created_by varchar(50),
  	created_date timestamp without time zone ,
  	constraint move_pk primary key (move_id) ,
  	constraint participant_move_fk foreign key (participant_id)
  	references public.participant(participant_id) match simple 
  	on update no action  ,
  	constraint game_fk foreign key (game_id)
  	references public.game(game_id) match simple 
  	on update no action ,
  	constraint step_fk foreign key (step_id)
  	references public.step_master(step_id) match simple 
  	on update no action ,
  	constraint token_fk foreign key (token_id)
  	references public.token_master(token_id) match simple 
  	on update no action
);


-----------------------------------------------------------------------------------------

drop sequence if exists public.move_event_junction_seq cascade;

CREATE SEQUENCE public.move_event_junction_seq
start with 1
increment by 1
minvalue 0
maxvalue 100000
cycle;


drop table if exists public.move_event_junction cascade;

create table public.move_event_junction  (
	move_event_junction_id integer NOT NULL DEFAULT (nextval('public.move_event_junction_seq')::regclass) ,
	event_id integer ,
	move_id integer ,
	created_by varchar(50),
  	created_date timestamp without time zone ,
	constraint move_jtn_fk foreign key (move_id)
  	references public.move(move_id) match simple 
  	on update no action ,
  	constraint event_jtn_fk foreign key (event_id)
  	references public.event_master(event_id) match simple 
  	on update no action 
);
