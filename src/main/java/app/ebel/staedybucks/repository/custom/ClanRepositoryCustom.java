package app.ebel.staedybucks.repository.custom;

import app.ebel.staedybucks.dto.base.ClanInfoDto;

import java.util.List;

public interface ClanRepositoryCustom {

    List<ClanInfoDto> findAllClans();
}
