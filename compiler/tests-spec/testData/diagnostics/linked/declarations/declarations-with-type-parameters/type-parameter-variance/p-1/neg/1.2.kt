// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: out type parameter used in invariant parameter position, as mutable property type, and in return of in-box are rejected
 */

// TESTCASE NUMBER: 1
class Inv<T>

class OutBox<out T> {
    fun useInvariant(holder: Inv<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>>) {}
}

// TESTCASE NUMBER: 2
class OutBox2<out T> {
    var slot: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!> = TODO()
}

// TESTCASE NUMBER: 3
class InBox<in T> {
    fun produceInvariant(): Inv<<!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>> = TODO()
}
