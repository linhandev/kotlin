// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 69 -> sentence 69
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 69 -> sentence 69
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: as cast to interface then invoke convention
 */

// TESTCASE NUMBER: 1
interface Invokable {
    operator fun invoke(): Int
}

class Impl : Invokable {
    override operator fun invoke(): Int = 42
}

fun test(obj: Any): Int = (obj as Invokable)()

fun box(): String {
    if (test(Impl()) != 42) return "NOK"
    return "OK"
}
