package com.example.demo.service;

import com.example.demo.repository.entity.Player;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.utils.CommonUtils;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<Player> of(GetPlayersDto getPlayersDto) {
        return Specification
                .where(hasName(getPlayersDto))
                .and(hasTitle(getPlayersDto))
                .and(hasRace(getPlayersDto))
                .and(hasProfession(getPlayersDto))
                .and(hasProfession(getPlayersDto))
                .and(hasDatesRange(getPlayersDto))
                .and(hasBanned(getPlayersDto))
                .and(hasExperience(getPlayersDto))
                .and(hasLevel(getPlayersDto))
                ;
    }

    private static Specification<Player> hasName(GetPlayersDto getPlayersDto) {
        return getPlayersDto.getName() == null ? null : (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + getPlayersDto.getName().toLowerCase() + "%");
    }

    private static Specification<Player> hasTitle(GetPlayersDto getPlayersDto) {
        return getPlayersDto.getTitle() == null ? null : (root, query, cb) ->
                cb.like(cb.lower(root.get("title")), "%" + getPlayersDto.getTitle().toLowerCase() + "%");
    }

    private static Specification<Player> hasRace(GetPlayersDto getPlayersDto) {
        return getPlayersDto.getRace() == null ? null : (root, query, cb) ->
                cb.equal(root.get("race"), getPlayersDto.getRace());
    }

    private static Specification<Player> hasProfession(GetPlayersDto getPlayersDto) {
        return getPlayersDto.getProfession() == null ? null : (root, query, cb) ->
                cb.equal(root.get("profession"), getPlayersDto.getRace());
    }

    private static Specification<Player> hasBanned(GetPlayersDto getPlayersDto) {
        return getPlayersDto.getBanned() == null ? null : (root, query, cb) ->
                cb.equal(root.get("banned"), getPlayersDto.getBanned());
    }

    private static Specification<Player> hasDatesRange(GetPlayersDto getPlayersDto) {
        Specification<Player> specification = null;

        if (getPlayersDto.getAfter() != null && getPlayersDto.getBefore() != null) {
            specification = ((root, query, cb) ->
                    cb.between(
                            root.get("birthday"),
                            CommonUtils.convertLongToLocalDate(getPlayersDto.getAfter()),
                            CommonUtils.convertLongToLocalDate(getPlayersDto.getBefore()))
            );
        } else if (getPlayersDto.getAfter() != null) {
            specification = ((root, query, cb) ->
                    cb.greaterThan(root.get("birthday"), CommonUtils.convertLongToLocalDate(getPlayersDto.getAfter())));
        } else if (getPlayersDto.getBefore() != null) {
            specification = ((root, query, cb) ->
                    cb.lessThan(root.get("birthday"), CommonUtils.convertLongToLocalDate(getPlayersDto.getBefore())));
        }
        return specification;
    }

    private static Specification<Player> hasExperience(GetPlayersDto getPlayersDto) {
        Specification<Player> specification = null;

        if (getPlayersDto.getMinExperience() != null && getPlayersDto.getMaxExperience() != null) {
            specification = ((root, query, cb) ->
                    cb.between(root.get("experience"), getPlayersDto.getMinExperience(), getPlayersDto.getMaxExperience())
            );
        } else if (getPlayersDto.getMinExperience() != null) {
            specification = ((root, query, cb) ->
                    cb.greaterThan(root.get("experience"), getPlayersDto.getMinExperience()));
        } else if (getPlayersDto.getMaxExperience() != null) {
            specification = ((root, query, cb) ->
                    cb.lessThan(root.get("experience"), getPlayersDto.getMaxExperience()));
        }
        return specification;
    }

    private static Specification<Player> hasLevel(GetPlayersDto getPlayersDto) {
        Specification<Player> specification = null;

        if (getPlayersDto.getMinLevel() != null && getPlayersDto.getMaxLevel() != null) {
            specification = ((root, query, cb) ->
                    cb.between(root.get("level"), getPlayersDto.getMinExperience(), getPlayersDto.getMaxExperience())
            );
        } else if (getPlayersDto.getMinLevel() != null) {
            specification = ((root, query, cb) ->
                    cb.greaterThan(root.get("level"), getPlayersDto.getMinLevel()));
        } else if (getPlayersDto.getMaxLevel() != null) {
            specification = ((root, query, cb) ->
                    cb.lessThan(root.get("level"), getPlayersDto.getMaxLevel()));
        }
        return specification;
    }
}
