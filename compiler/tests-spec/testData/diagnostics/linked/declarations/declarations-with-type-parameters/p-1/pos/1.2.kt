// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: interfaces, extension properties and type aliases may use type parameters
 */

// TESTCASE NUMBER: 1
interface Repository<T> {
    fun load(): T
}

// TESTCASE NUMBER: 2
val <T> List<T>.head: T
    get() = first()

// TESTCASE NUMBER: 3
class Holder<T>(val value: T)

typealias IntHolder = Holder<Int>
