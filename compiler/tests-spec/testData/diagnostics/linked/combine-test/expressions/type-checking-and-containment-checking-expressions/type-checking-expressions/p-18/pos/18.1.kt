// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 18 -> sentence 18
 *                declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: precise type inference for bare type is check inside inline function with reified type parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
class Fee<T>(val t: T) : Foo<T>

inline fun <reified T> checkAndGet(foo: Foo<T>): T? {
    if (foo is Fee) {
        return foo.t
    }
    return null
}

fun case1() {
    val fee: Foo<String> = Fee("hello")
    checkSubtype<String?>(checkAndGet<String>(fee))
}
