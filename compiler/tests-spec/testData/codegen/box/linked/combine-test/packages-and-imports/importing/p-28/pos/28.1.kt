// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, packages-and-imports, importing -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: packages-and-imports, importing -> paragraph 28 -> sentence 28
 *                packages-and-imports, modules -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: Java interop types from java.util can be imported and used
 */
import java.util.ArrayList

// TESTCASE NUMBER: 1
fun test(): Int = ArrayList<Int>().apply { add(1) }.size

fun box(): String {
    if (test() != 1) return "NOK"
    val xs = ArrayList<Int>()
    xs.add(2)
    xs.add(3)
    if (xs.size != 2) return "NOK"
    return "OK"
}
