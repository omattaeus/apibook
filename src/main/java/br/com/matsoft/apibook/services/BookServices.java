package br.com.matsoft.apibook.services;

import br.com.matsoft.apibook.controller.BookController;
import br.com.matsoft.apibook.data.vo.v1.BookVO;
import br.com.matsoft.apibook.exceptions.RequiredObjectIsNullException;
import br.com.matsoft.apibook.exceptions.ResourceNotFoundException;
import br.com.matsoft.apibook.mapper.DozerMapper;
import br.com.matsoft.apibook.models.Book;
import br.com.matsoft.apibook.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    PagedResourcesAssembler<BookVO> assembler;

    public List<BookVO> findAll() {

        logger.info("Finding all book!");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
        return books;
    }

    public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {

        logger.info("Finding all Books!");

        var personPage = repository.findAll(pageable);

        var personVosPage = personPage.map(p -> DozerMapper.parseObject(p, BookVO.class));
        personVosPage.map(p -> {
            try {
                return p.add(linkTo(methodOn(BookController.class)
                        .findById(p.getKey())).withSelfRel());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        Link link = linkTo(methodOn(BookController.class).findAll(pageable.getPageNumber(),
                pageable.getPageSize(), "asc")).withSelfRel();

        return assembler.toModel(personVosPage, link);
    }

    public BookVO findById(Long id) {

        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book!");

        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var vo =  DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    @Transactional
    public BookVO disableBook(Long id) throws Exception {

        logger.info("Disabling one book!");

        repository.disableBook(id);

        var entity = repository.findById(id)
                .orElseThrow(() -> new RequiredObjectIsNullException("No records found for this ID!"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}
