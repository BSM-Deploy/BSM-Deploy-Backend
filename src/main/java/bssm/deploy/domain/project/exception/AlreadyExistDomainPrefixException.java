package bssm.deploy.domain.project.exception;

import bssm.deploy.global.error.exceptions.BadRequestException;
import com.google.common.collect.ImmutableMap;

public class AlreadyExistDomainPrefixException extends BadRequestException {
    public AlreadyExistDomainPrefixException() {
        super(ImmutableMap.<String, String>builder().
                put("domainPrefix", "이미 존재하는 도메인 접두사입니다.").
                build());
    }
}