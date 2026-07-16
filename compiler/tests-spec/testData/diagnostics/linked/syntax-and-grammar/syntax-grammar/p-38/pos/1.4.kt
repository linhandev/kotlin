// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 38 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: propertyDelegate by object expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p38.pos4

class Delegate {
    operator fun getValue(thisRef: Any?, property: kotlin.reflect.KProperty<*>): Int = 1
}

val value by Delegate()
