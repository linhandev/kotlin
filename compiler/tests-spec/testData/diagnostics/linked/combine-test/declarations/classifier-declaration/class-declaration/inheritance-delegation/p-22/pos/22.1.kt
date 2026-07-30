// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: provideDelegate validates property name and getValue returns it
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class ValidatingDelegate {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>) =
        if (property.name == "valid") this else error("invalid")

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = property.name
}

class Box {
    val valid: String by ValidatingDelegate()
}

fun case_1() {
    checkSubtype<String>(Box().valid)
}
