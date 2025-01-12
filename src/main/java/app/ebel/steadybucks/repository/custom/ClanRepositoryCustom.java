package app.ebel.steadybucks.repository.custom;

import app.ebel.steadybucks.dto.base.ClanInfoDto;

import java.util.List;

public interface ClanRepositoryCustom {

    List<ClanInfoDto> findAllClans();
}
