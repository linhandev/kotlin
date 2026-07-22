/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 17
 * DESCRIPTION: extension function-like callable resolves as receiver.extFn()
 */

fun String.echo1153(): String = this

// TESTCASE NUMBER: 1
fun box(): String = if ("OK".echo1153() == "OK") "OK" else "NOK"
