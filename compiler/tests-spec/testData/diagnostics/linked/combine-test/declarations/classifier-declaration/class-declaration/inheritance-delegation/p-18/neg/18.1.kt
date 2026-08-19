// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: delegated property cannot have custom getter
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = 42
}

class Box {
    val x: Int by Delegate()
        <!ACCESSOR_FOR_DELEGATED_PROPERTY!>get() = 84<!>
}

fun case_1() = Box().x
