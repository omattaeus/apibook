package br.com.matsoft.apibook.services;

import br.com.matsoft.apibook.controller.BookController;
import br.com.matsoft.apibook.data.vo.v1.BookVO;
import br.com.matsoft.apibook.exceptions.RequiredObjectIsNullException;
import br.com.matsoft.apibook.mapper.DozerMapper;
import br.com.matsoft.apibook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class UserServices implements UserDetailsService {

    private final Logger logger = Logger.getLogger(UserServices.class.getName());

    @Autowired
    UserRepository repository;

    public UserServices(UserRepository repository) {
        this.repository = repository;
    }

    public BookVO findById(Long id) throws Exception {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        logger.info("Finding one user by name" + username + "!");

        var user = repository.findByUsername(username);

        if(user != null){
            return user;
        } else {
            throw new UsernameNotFoundException("Username" + username + " not found!");
        }
    }
}
