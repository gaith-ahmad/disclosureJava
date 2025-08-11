package com.gosi.disclosure.service;

import com.gosi.disclosure.domain.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import tech.jhipster.config.JHipsterProperties;

/**
 * Service for sending emails asynchronously.
 * <p>
 * We use the {@link Async} annotation to send emails asynchronously.
 */
@Service
public class OldSharPintService{

	  
//
		  getDisclosureItemByEmail(email: string): Observable<any> {
		    const url = `${environment.apiUrl}Disclosure/GetItemByMail?Email=${email}`;
		    return this.http.get<DisclosureDTO[]>(url);
		  }
//
//		  createDisclosureNewItem(disclosureRequest: DisclosureRequestDTO) {
//		    return this.http.post<DisclosureDTO>(
//		      `${environment.apiUrl}Disclosure/CreateNewitem`,
//		      disclosureRequest
//		    );
//		  }
//
//		  getLDAPUserByEmail(email: string) {
//		    return this.http.get<UserRelativeDTO>(
//		      `${environment.apiUrl}PDAP/GetEmpInfoByEmail?email=${email}`
//		    );
//		  }
//
//		  getLDAPUserByUserId(userId: string) {
//		    return this.http.get<UserDTO>(
//		      `${environment.apiUrl}PDAP/GetEmpInfoByUserId?userId=${userId}`
//		    );
//		  }
//
//		  getLDAPUserByName(name: string) {
//		    return this.http.get<EmployeeDTO[]>(
//		      `${environment.apiUrl}PDAP/GetUsersByName?name=${name}`
//		    );
//		  }
//
//		  // New APIs
//		  getUserInfo() {
//		    return this.http.get<UserDTO>(`${environment.apiUrl}api/GetUserInfo`);
//		  }
//
//		  getHistory(): Observable<any> {
//		    const url = `${environment.apiUrl}Disclosure/GetDisclosureUserHistory`;
//		    return this.http.get<DisclosureDTO[]>(url);
//		  }
}