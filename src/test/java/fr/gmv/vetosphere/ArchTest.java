package fr.gmv.vetosphere;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("fr.gmv.vetosphere");

        noClasses()
            .that()
            .resideInAnyPackage("fr.gmv.vetosphere.service..")
            .or()
            .resideInAnyPackage("fr.gmv.vetosphere.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..fr.gmv.vetosphere.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
