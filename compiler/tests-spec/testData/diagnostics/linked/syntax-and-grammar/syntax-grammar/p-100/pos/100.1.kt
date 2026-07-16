// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 100 -> sentence 100
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: assignableSuffix navigation suffix on assign target
 *
 * NOTE: assignableSuffix BNF is typeArguments | indexingSuffix | navigationSuffix.
 * A positive case where assignableSuffix is only typeArguments cannot be written in
 * valid Kotlin: there is no realistic LHS like `foo<Int> = expr` (typeArguments
 * before `=` is not a directly-assignable target). Explicit type arguments on calls
 * are parsed as postfixUnarySuffix typeArguments before callSuffix, not as
 * assignableSuffix; see p-95/pos/95.2.kt (`id<Int>(1)`). indexingSuffix assign
 * is covered in p-96/pos/96.2.kt (`a[0] = 1`); this file covers navigationSuffix.
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p100.pos1

class Holder {
    var value = 0
}

fun case1() {
    val h = Holder()
    h.value = 1
}
