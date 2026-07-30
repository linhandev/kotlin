// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: provideDelegate returns property name via getValue
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class Delegate {
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>) = this
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = property.name
}

class Box {
    val x: String by Delegate()
}

fun case_1() {
    checkSubtype<String>(Box().x)
}
