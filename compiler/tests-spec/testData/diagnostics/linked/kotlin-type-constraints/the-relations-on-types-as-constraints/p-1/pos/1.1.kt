// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: GLB converted as intersection type allows members from all intersected bounds
 * HELPERS: checkType
 */

interface IA1323 { fun a(): Int }
interface IB1323 { fun b(): String }

class Both1323 : IA1323, IB1323 {
    override fun a() = 1
    override fun b() = "ok"
}

fun <T> useGlb1323(x: T) where T : IA1323, T : IB1323 {
    x.a()
    x.b()
}

// TESTCASE NUMBER: 1
fun case_1(value: Both1323) {
    useGlb1323(value)
    checkSubtype<IA1323>(value)
    checkSubtype<IB1323>(value)
}
