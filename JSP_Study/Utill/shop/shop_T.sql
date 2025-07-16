
CREATE TABLE shop_T	(
  num		BIGINT			Not Null  AUTO_INCREMENT,
  category	 varchar(10)		Not Null,
  p_num		 varchar(10)		Not Null,
  p_name		 varchar(50)		Not Null,
  p_company varchar(50)		Not Null,
  p_price		 BIGINT			Not Null,
  p_saleprice 	BIGINT			Not Null,
  p_image_s	 varchar(50)		Null,
  p_image_l	 varchar(50)		Null,
  p_content	 varchar(4000)	Not Null,
  p_date		 date		Not Null,
  CONSTRAINT shop_t_pk PRIMARY KEY(num)
) ; 


insert into shop_t(category, p_num, p_name, p_company,
p_price, p_saleprice, p_image_s, 
p_image_l, p_content, p_date)
 values('sp003', 'RC-113',
'��ü�� �ζ���','��ü��',3200,1150,'pds1.jpg','pds1_z.jpg',
'���̿��ƽ� ��ǳ ���Ϸ�-HGPU SHELL * Ư�� ��� ��� ��â * �Ź߲� �޸� ��Ŭ * �� ���� ���� �ż��� �Ź߲� �ý��� * ���� �޸� ������ ���� ����� ��ǳ���� ������ ���̳� * �� ��ǳ �ý��� * ��ǳ���� ��ü������ �Ź߹�â * �ս��� ��Ʈ�� �ý���(�ű� ���� �Ա�) * ����� �˷�̴� ������ * 80mm 82a hyper dubbs �� * ��ö �����̼� * ABEC-5 ���', NOW());

insert into shop_t(category, p_num, p_name, p_company,
p_price, p_saleprice, p_image_s, 
p_image_l, p_content, p_date)
 values('ele002', 'vC-13',
'���PDP-TV','���',9200,4750,'pds4.jpg','pds4_z.jpg',
'����~ ����! 
������ ������~ ��ȸ ���� �ʾ�~~',NOW());

insert into shop_t(category, p_num, p_name, p_company,
p_price, p_saleprice, p_image_s, 
p_image_l, p_content, p_date)
 values('ele002', 'vC-111',
'LG ����� �������÷���','LG����',2000000,1820000,'dios_s.PNG','dios_l.PNG',
'���� �Դ� ����, �츮���� ����, ������ �ƺ��� �ǰ��ֽ��� ���� ���� �����Ͽ� ����� ���ϰ� ����� �� �ִ� ������ �����Դϴ�.',NOW());
