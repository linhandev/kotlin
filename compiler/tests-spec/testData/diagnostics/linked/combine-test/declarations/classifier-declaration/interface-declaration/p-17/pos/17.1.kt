// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 17 -> sentence 17
 *                declarations, declarations-with-type-parameters -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: type inference for generic interface default function using type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box<T> {
    fun empty(): T? = null
}

class IntBoxImpl : Box<Int>

fun case1() {
    val b = IntBoxImpl()
    checkSubtype<IntBoxImpl>(b)
    checkSubtype<Int?>(b.empty())
    checkSubtype<Box<Int>>(b)
}
