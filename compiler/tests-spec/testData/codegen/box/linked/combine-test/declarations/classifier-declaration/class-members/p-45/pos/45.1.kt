// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 45 -> sentence 45
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: member reference ::invoke accepted as () -> Int
 */

// TESTCASE NUMBER: 1

class Callable {
    operator fun invoke(): Int = 42
}

fun accept(fn: () -> Int) = fn()

fun test(): Int {
    val c = Callable()
    return accept(c::invoke)
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
