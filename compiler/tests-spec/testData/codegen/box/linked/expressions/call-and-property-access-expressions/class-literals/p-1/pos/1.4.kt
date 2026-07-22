// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: class literal on string and integer literals yields KClass of compile-time types
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if ("abc"::class != String::class) return "NOK"
    if (1::class != Int::class) return "NOK"
    return "OK"
}
