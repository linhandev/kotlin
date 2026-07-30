// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: provideDelegate validates property name and getValue returns it
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

fun test() = Box().valid

fun box(): String {
    if (test() != "valid") return "NOK"
    return "OK"
}
