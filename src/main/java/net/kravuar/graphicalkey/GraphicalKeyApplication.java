package net.kravuar.graphicalkey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties({LocalizationProps.class, AuthProps.class})
public class GraphicalKeyApplication {
    public static void main(String[] args) {
        SpringApplication.run(GraphicalKeyApplication.class, args);
    }

    @Bean
    public MessageSource messageSource(LocalizationProps localizationProps) {
        Locale.setDefault(Locale.forLanguageTag(localizationProps.defaultLocale()));
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/i18n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }
    @Bean
    public LocalValidatorFactoryBean validator(LocalizationProps localizationProps) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource(localizationProps));
        return bean;
    }
}
