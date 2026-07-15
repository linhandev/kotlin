// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 38 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: propertyDelegate var by custom delegate
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p38.pos5

class MutableDelegate {
    private var stored = 0
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Int = stored
    operator fun setValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>, value: Int) {
        stored = value
    }
}

var value by MutableDelegate()
