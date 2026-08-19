// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 18 -> sentence 18
 *                declarations, declarations-with-type-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: type inference for generic interface default getter with String and Int type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Holder<T> {
    val value: T? get() = null
}

class StringHolder : Holder<String>

class IntHolder : Holder<Int>

fun case1() {
    val s = StringHolder()
    checkSubtype<StringHolder>(s)
    checkSubtype<String?>(s.value)
    checkSubtype<Holder<String>>(s)
    val i = IntHolder()
    checkSubtype<IntHolder>(i)
    checkSubtype<Int?>(i.value)
    checkSubtype<Holder<Int>>(i)
}
