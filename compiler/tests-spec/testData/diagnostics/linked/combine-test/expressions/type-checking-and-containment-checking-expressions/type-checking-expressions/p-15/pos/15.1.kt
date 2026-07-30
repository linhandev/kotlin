// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -USELESS_IS_CHECK -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 15 -> sentence 15
 *                type-system, type-kinds, intersection-types -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: bare type is check with intersection type smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Foo<A>
interface Bar {
    fun bar(): String
}
class Fee<T>(val t: T) : Foo<T>, Bar {
    override fun bar(): String = "bar"
}

fun case1() {
    val foo: Foo<String> = Fee("hello")
    if (foo is Fee && foo is Bar) {
        checkSubtype<String>(foo.t)
        checkSubtype<String>(foo.bar())
    }
}
