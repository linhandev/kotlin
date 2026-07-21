/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: exceptions, throwing-exceptions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: throw accepts user-defined exception type value
 */
// TESTCASE NUMBER: 1

class DomainError1612 : Exception("domain")

fun box(): String {
    return try {
        throw DomainError1612()
    } catch (e: DomainError1612) {
        if (e.message == "domain") "OK" else "NOK"
    }
}
