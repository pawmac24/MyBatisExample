package org.mybatis.example;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.mybatis.example.beans.Contact;
import org.mybatis.example.mappers.ContactMapper;

public class ExampleApplication {

	private static SqlSessionFactory sqlMapper = null;
	private static final Logger log = Logger
			.getLogger(ExampleApplication.class);

	public static void main(String[] args) {
		String resource = "org/mybatis/example/configuration.xml";
		Reader reader = null;

		try {
			reader = Resources.getResourceAsReader(resource);
			sqlMapper = new SqlSessionFactoryBuilder().build(reader);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		// select all
		SqlSession session = sqlMapper.openSession();
		{
			try {
				log.info("===== before insert =====");
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				List<Contact> contacts = mapper.selectAll();
				for (Contact contact : contacts) {
					log.info("   " + contact.getEmail() + ":"
							+ contact.getFirstName() + ":"
							+ contact.getLastName());
				}
			}
			finally {
				session.close();
			}
		}
		// insert
		session = sqlMapper.openSession();
		{
			try {
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				Contact c1 = new Contact();
				c1.setId(2);
				c1.setEmail("abc@abc.pl");
				c1.setFirstName("Pawel");
				c1.setLastName("Mackiewicz");
				c1.setPhone("111-22-33");
				mapper.insert(c1);
				session.commit();
			}
			finally {
				session.close();
			}
		}
		// select all
		session = sqlMapper.openSession();
		{
			try {
				log.info("===== After insert =====");
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				List<Contact> contacts = mapper.selectAll();
				for (Contact contact : contacts) {
					log.info("   " + contact.getEmail() + ":"
							+ contact.getFirstName() + ":"
							+ contact.getLastName());
				}
			}
			finally {
				session.close();
			}
		}
		// update
		session = sqlMapper.openSession();
		{
			try {
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				Contact c1 = mapper.select(2);
				c1.setFirstName("Pawel Marcin");
				mapper.update(c1);
				session.commit();
			}
			finally {
				session.close();
			}
		}
		// select all after ipdate
		session = sqlMapper.openSession();
		{
			try {
				log.info("===== After update =====");
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				List<Contact> contacts = mapper.selectAll();
				for (Contact contact : contacts) {
					log.info("   " + contact.getEmail() + ":"
							+ contact.getFirstName() + ":"
							+ contact.getLastName());
				}
			}
			finally {
				session.close();
			}
		}
		// delete
		session = sqlMapper.openSession();
		{
			try {
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				mapper.delete(2);
				session.commit();
			}
			finally {
				session.close();
			}
		}
		// select all after delete
		session = sqlMapper.openSession();
		{
			try {
				log.info("===== After delete =====");
				ContactMapper mapper = session.getMapper(ContactMapper.class);
				List<Contact> contacts = mapper.selectAll();
				for (Contact contact : contacts) {
					log.info("   " + contact.getEmail() + ":"
							+ contact.getFirstName() + ":"
							+ contact.getLastName());
				}
			}
			finally {
				session.close();
			}
		}
	}
}
