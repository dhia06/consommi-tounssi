// package tn.esprit.spring.control;
//
//
//
//
//
//
// import java.io.IOException;
//
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Collection;
// import java.util.Date;
// import java.util.List;
// import java.util.Optional;
//
// import javax.annotation.PostConstruct;
// import javax.faces.context.ExternalContext;
// import javax.faces.context.FacesContext;
// import javax.servlet.http.Cookie;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// import org.ocpsoft.rewrite.el.ELBeanName;
// import org.springframework.beans.factory.annotation.Autowired;
//
//
// import org.springframework.security.authentication.AuthenticationManager;
// import
// org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import tn.esprit.spring.entities.User;
// import tn.esprit.spring.repository.RoleRepository;
// import tn.esprit.spring.repository.UserRepository;
// import tn.esprit.spring.security.JwtTokenProvider;
// import tn.esprit.spring.service.UserService;
// import tn.esprit.spring.servlet.NavigateTo;
//
//
// //@RestController
//
// @RequestMapping("/api/auth/jsf")
// @Controller(value = "SigninJsf")
// @ELBeanName(value = "SigninJsf")
//
// public class SigninJsf {
//
// // @Autowired
// // RestTemplate restTemplate;
//
// @Autowired
// AuthenticationManager authenticationManager;
//
// @Autowired
// UserRepository userRepository;
// @Autowired
// UserService UserService;
//
// @Autowired
// RoleRepository roleRepository;
//
// @Autowired
// PasswordEncoder passwordEncoder;
//
// @Autowired
// JwtTokenProvider tokenProvider;
//
// private String username;
// private String password;
// private String token;
// private Date date;
//
// public Date getDate() {
// return date;
// }
//
//
// public void setDate(Date date) {
// this.date = date;
// }
//
// public String getToken() {
// return token;
// }
//
// public String getUsername() {
// return username;
// }
//
// public void setUsername(String username) {
// this.username = username;
// }
//
// public String getPassword() {
// return password;
// }
//
// public void setPassword(String password) {
// this.password = password;
// }
//
// public void setToken(String token) {
// this.token = token;
// }
//
// public void doLogin() {
//
// String userName = null;
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
//
// }
//
//
// public boolean nameuser(){
//
// String userName = null;
// if(SecurityContextHolder.getContext().getAuthentication() == null){
//
// return false;}
//
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
// if(userName.equals("anonymousUser"))
// return false ;
// else
// return true ;
// }
//
//
// public String nameuserC(){
//
// String userName = null;
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
// if(userName.equals("anonymousUser"))
// return "" ;
// else return userName;
//
// }
//
//
//
//
// public String logout () throws IOException {
// //
// // HttpServletRequest response =response. ;
// //
// // response.logout();
// //
//
// ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
// HttpServletRequest request = (HttpServletRequest) ex.getRequest();
//
// HttpServletResponse response = (HttpServletResponse) ex.getResponse();
//
// Cookie[] cookies = request.getCookies();
//
// Optional<Cookie> token = Arrays.stream(cookies).filter(cookie ->
// cookie.getName().equals("token")).findFirst();
//
// token.get().setValue(null);
//
// response.addCookie(token.get());
//
// // response.sendRedirect("http://localhost:8081/login");
//
// return "/login/name.xhtml?faces-redirect=true";
// }
//
// public String getroleuser() {
//
// User u ;
// String userName = null;
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
// u = userRepository.findByUsername(userName).get();
//
// String rolesname;
// rolesname = u.getRoles().stream().findFirst().get().getName().name();
//
// return rolesname;
// }
//
//
// public Long idusercurrent() {
//
// User u ;
// String userName = null;
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
// u = userRepository.findByUsername(userName).get();
//
// return u.getId();
// }
//
//
//
// public String signin() {
// NavigateTo nav =new NavigateTo();
//
// Authentication authentication = authenticationManager
// .authenticate(new UsernamePasswordAuthenticationToken(username, password));
// SecurityContextHolder.getContext().setAuthentication(authentication);
//
// String jwt = tokenProvider.generateToken(authentication);
//
//
// this.token = jwt;
//
//
// String userName = null;
// Object principal =
// SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
// if (principal instanceof UserDetails) {
// userName = ((UserDetails) principal).getUsername();
// } else {
// userName = principal.toString();
// }
//
// Collection<? extends GrantedAuthority> authorities =
// authentication.getAuthorities();
// List<String> roles = new ArrayList<String>();
// for (GrantedAuthority a : authorities) {
// roles.add(a.getAuthority());
// }
//
// String url = "";
// if (roles.contains("ROLE_USER"))
// return nav.navigateToHomeProduct();
//
// if (roles.contains("ROLE_DELIVERYMAN"))
// url = "/name.xhtml?faces-redirect=true";
//
// if (roles.contains("ROLE_ADMIN"))
// return nav.adminHomeProduct();
// if (roles.contains("ROLE_Supplier"))
// return nav.supplierHome();
//
//
//
//
// ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
// HttpServletRequest request = (HttpServletRequest) ex.getRequest();
//
//
//
// return url;
// }
//
//
//
// }
//
//
//
//
//
//
