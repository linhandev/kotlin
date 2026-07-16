/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, determining-function-applicability-for-a-specific-call, description -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-lambda argument inferred type conforms to parameter type T_i <: U_j
 */

open class Base11302

class Derived11302 : Base11302()

fun pick11302(b: Base11302): String = "ok"

// TESTCASE NUMBER: 1
fun box(): String = if (pick11302(Derived11302()) == "ok") "OK" else "NOK"
