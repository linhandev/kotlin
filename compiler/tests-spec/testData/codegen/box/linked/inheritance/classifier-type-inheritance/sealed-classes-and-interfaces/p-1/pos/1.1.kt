// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: inheritance, classifier-type-inheritance, sealed-classes-and-interfaces -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: exhaustive when on sealed class returns correct value at runtime
 */

// TESTCASE NUMBER: 1
sealed class Result512 {
    class Ok(val value: Int) : Result512()
    class Err(val message: String) : Result512()
}

fun describe(r: Result512): String {
    return when (r) {
        is Result512.Ok -> "ok:${r.value}"
        is Result512.Err -> "err:${r.message}"
    }
}

fun box(): String {
    return if (describe(Result512.Ok(7)) == "ok:7") "OK" else "NOK"
}
