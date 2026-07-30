// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, property-declaration, delegated-property-declaration -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: delegated property cannot have custom setter
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class Delegate {
    var v = 0
    operator fun getValue(thisRef: Any?, property: KProperty<*>) = v
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        v = value
    }
}

class Box {
    var x: Int by Delegate()
        <!ACCESSOR_FOR_DELEGATED_PROPERTY!>set(value) {}<!>
}

fun case_1() {
    val b = Box()
    b.x = 10
    b.x
}
