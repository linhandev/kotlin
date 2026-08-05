// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 18 -> sentence 18
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 18 -> sentence 18
 *                expressions, when-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: duplicate import of same declaration remains legal and usable in when
 */
import kotlin.math.PI
import kotlin.math.PI

// TESTCASE NUMBER: 1
fun test(): Double = when {
    PI > 3.0 -> PI
    else -> 0.0
}

fun box(): String {
    if (test() <= 3.0 || test() >= 4.0) return "NOK"
    return "OK"
}
