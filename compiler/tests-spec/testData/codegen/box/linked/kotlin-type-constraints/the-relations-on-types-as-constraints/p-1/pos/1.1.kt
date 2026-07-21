/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: GLB constraint is intersection type enabling access to both supertypes
 */

interface A1323 { fun fa(): Int }
interface B1323 { fun fb(): Int }

class C1323 : A1323, B1323 {
    override fun fa() = 1
    override fun fb() = 2
}

fun <T> glb1323(x: T): Int where T : A1323, T : B1323 = x.fa() + x.fb()

// TESTCASE NUMBER: 1
fun box(): String = if (glb1323(C1323()) == 3) "OK" else "NOK"
