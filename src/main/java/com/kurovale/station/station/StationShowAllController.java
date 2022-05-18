package com.kurovale.station.station;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class StationShowAllController
{
    private final StationRepository repository;
    private final StationModelAssembler assembler;

    @GetMapping("/stations")
    ResponseEntity<?> showAll()
    {
        return showAllPaginated(1);
    }

    @GetMapping(value = "/stations", params = {"page"})
    ResponseEntity<?> showAllPaginated(@RequestParam(value = "page") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByActiveIsTrue(pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllPaginated(pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllPaginated(pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllPaginated(page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllPaginated(1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllPaginated(stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"name"})
    ResponseEntity<?> showAllByName(@RequestParam(value = "name") String name)
    {
        return showAllByNamePaginated(name, 1);
    }

    @GetMapping(value = "/stations", params = {"name", "page"})
    ResponseEntity<?> showAllByNamePaginated(@RequestParam(value = "name") String name, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByNameLikeAndActiveIsTrue("%" + name + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNamePaginated(name, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNamePaginated(name, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByNamePaginated(name, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByNamePaginated(name, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByNamePaginated(name, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"address"})
    ResponseEntity<?> showAllByAddress(@RequestParam(value = "address") String address)
    {
        return showAllByAddressPaginated(address, 1);
    }

    @GetMapping(value = "/stations", params = {"address", "page"})
    ResponseEntity<?> showAllByAddressPaginated(@RequestParam(value = "address") String address, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByAddressLikeAndActiveIsTrue("%" + address + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByAddressPaginated(address, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByAddressPaginated(address, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByAddressPaginated(address, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByAddressPaginated(address, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByAddressPaginated(address, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"name", "address"})
    ResponseEntity<?> showAllByNameAndAddress(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address)
    {
        return showAllByNameAndAddressPaginated(name, address, 1);
    }

    @GetMapping(value = "/stations", params = {"name", "address", "page"})
    ResponseEntity<?> showAllByNameAndAddressPaginated(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByNameLikeAndAddressLikeAndActiveIsTrue("%" + name + "%", "%" + address + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAndAddressPaginated(name, address, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAndAddressPaginated(name, address, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByNameAndAddressPaginated(name, address, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAndAddressPaginated(name, address, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAndAddressPaginated(name, address, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"phone"})
    ResponseEntity<?> showAllByPhone(@RequestParam(value = "phone") String phone)
    {
        return showAllByPhonePaginated(phone, 1);
    }

    @GetMapping(value = "/stations", params = {"phone", "page"})
    ResponseEntity<?> showAllByPhonePaginated(@RequestParam(value = "phone") String phone, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByPhoneLikeAndActiveIsTrue("%" + phone + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByPhonePaginated(phone, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByPhonePaginated(phone, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByPhonePaginated(phone, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByPhonePaginated(phone, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByPhonePaginated(phone, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"name", "phone"})
    ResponseEntity<?> showAllByNameAndPhone(@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone)
    {
        return showAllByNameAndPhonePaginated(name, phone, 1);
    }

    @GetMapping(value = "/stations", params = {"name", "phone", "page"})
    ResponseEntity<?> showAllByNameAndPhonePaginated(@RequestParam(value = "name") String name, @RequestParam(value = "phone") String phone, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByNameLikeAndPhoneLikeAndActiveIsTrue("%" + name + "%", "%" + phone + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAndPhonePaginated(name, phone, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAndPhonePaginated(name, phone, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByNameAndPhonePaginated(name, phone, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAndPhonePaginated(name, phone, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAndPhonePaginated(name, phone, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"address", "phone"})
    ResponseEntity<?> showAllByAddressAndPhone(@RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone)
    {
        return showAllByAddressAndPhonePaginated(address, phone, 1);
    }

    @GetMapping(value = "/stations", params = {"address", "phone", "page"})
    ResponseEntity<?> showAllByAddressAndPhonePaginated(@RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByAddressLikeAndPhoneLikeAndActiveIsTrue("%" + address + "%", "%" + phone + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByAddressAndPhonePaginated(address, phone, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByAddressAndPhonePaginated(address, phone, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByAddressAndPhonePaginated(address, phone, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByAddressAndPhonePaginated(address, phone, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByAddressAndPhonePaginated(address, phone, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }

    @GetMapping(value = "/stations", params = {"name", "address", "phone"})
    ResponseEntity<?> showAllByNameAddressAndPhone(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone)
    {
        return showAllByNameAddressAndPhonePaginated(name, address, phone, 1);
    }

    @GetMapping(value = "/stations", params = {"name", "address", "phone", "page"})
    ResponseEntity<?> showAllByNameAddressAndPhonePaginated(@RequestParam(value = "name") String name, @RequestParam(value = "address") String address, @RequestParam(value = "phone") String phone, @RequestParam(value = "page", defaultValue = "1") int page)
    {
        Pageable pageable = PageRequest.of(page - 1, 10);

        Page<Station> stations = repository.findByNameLikeAndAddressLikeAndPhoneLikeAndActiveIsTrue("%" + name + "%", "%" + address + "%", "%" + phone + "%", pageable);

        if (stations.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<StationDTO>> collectionModel = assembler.toCollectionModel(stations);

        if (stations.hasNext())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAddressAndPhonePaginated(name, address, phone, pageable.next().getPageNumber() + 1)).withRel("next"));
        }
        if (stations.hasPrevious())
        {
            collectionModel.add(
                    linkTo(methodOn(StationShowAllController.class).showAllByNameAddressAndPhonePaginated(name, address, phone, pageable.previousOrFirst().getPageNumber() + 1)).withRel("previous")
            );
        }
        collectionModel.add(linkTo(methodOn(StationShowAllController.class).showAllByNameAddressAndPhonePaginated(name, address, phone, page)).withSelfRel(),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAddressAndPhonePaginated(name, address, phone, 1)).withRel("first"),
                linkTo(methodOn(StationShowAllController.class).showAllByNameAddressAndPhonePaginated(name, address, phone, stations.getTotalPages())).withRel("last"));

        return ResponseEntity.ok().body(collectionModel);
    }
}
