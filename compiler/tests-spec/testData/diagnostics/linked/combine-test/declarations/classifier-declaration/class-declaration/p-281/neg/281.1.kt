// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 281 -> sentence 281
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 281 -> sentence 281
 *                declarations, property-declaration -> paragraph 281 -> sentence 281
 *                declarations, property-declaration, getters-and-setters -> paragraph 281 -> sentence 281
 * NUMBER: 1
 * DESCRIPTION: a var with private set cannot be assigned from outside the declaring class (INVISIBLE_SETTER); covers Int property, String property, and assignment from another top-level class; contrasts with next-point outside read success and with getters-and-setters p-3 declaration-only
 */

// TESTCASE NUMBER: 1
class Counter {
    var count: Int = 0
        private set
}

fun case1() {
    <!INVISIBLE_SETTER!>Counter().count<!> = 1
}

// TESTCASE NUMBER: 2
class LabelBox {
    var label: String = "x"
        private set
}

fun case2() {
    <!INVISIBLE_SETTER!>LabelBox().label<!> = "y"
}

// TESTCASE NUMBER: 3
class FlagHolder {
    var flag: Boolean = false
        private set
}

class FlagClient {
    fun set() {
        <!INVISIBLE_SETTER!>FlagHolder().flag<!> = true
    }
}
