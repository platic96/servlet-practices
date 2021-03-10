


desc emaillist;

guestbookguestbookguestbook-- insert
insert into emaillist
values(null,'정','민용','alsdyd0906@gamil.com');

-- list
select no,first_name,last_name,email from emaillist order by no desc;

-- delete
delete from emaillist where no = 3;
