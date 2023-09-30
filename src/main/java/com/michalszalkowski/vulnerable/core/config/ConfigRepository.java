package com.michalszalkowski.vulnerable.core.config;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {
}
