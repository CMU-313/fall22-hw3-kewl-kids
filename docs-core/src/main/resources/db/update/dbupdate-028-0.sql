create cached table T_FILE_RATING ( RAT_ID_C varchar(36) not null, RAT_IDFIL_C varchar(36) not null, RAT_IDUSER_C varchar(36) not null, RAT_ACADEMICS_C Int not null, RAT_ACTIVITIES_C Int not null, RAT_EXPERIENCE_C Int not null, RAT_AWARDS_C Int not null, RAT_OVERALL_C Int not null, RAT_COMMENT_C varchar(4000) not null, RAT_CREATEDATE_D datetime, RAT_DELETEDATE_D datetime, primary key (RAT_ID_C) );
alter table T_FILE_RATING add constraint FK_RAT_IDDOC_C foreign key (RAT_IDFIL_C) references T_FILE (FIL_ID_C) on delete restrict on update restrict;
alter table T_FILE_RATING add constraint FK_RAT_IDUSER_C foreign key (RAT_IDUSER_C) references T_USER (USE_ID_C) on delete restrict on update restrict;
update T_CONFIG set CFG_VALUE_C = '28' where CFG_ID_C = 'DB_VERSION';