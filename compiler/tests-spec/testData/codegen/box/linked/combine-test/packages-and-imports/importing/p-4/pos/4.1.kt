// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 4 -> sentence 4
 *                scopes-and-identifiers, identifiers-and-paths -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: import alias as resolves naming conflicts for imported top-level declarations
 */
import kotlin.math.PI as MathPI56004

// TESTCASE NUMBER: 1
fun test(): Double = MathPI56004

fun box(): String {
    if (test() <= 3.0 || test() >= 4.0) return "NOK"
    return "OK"
}
