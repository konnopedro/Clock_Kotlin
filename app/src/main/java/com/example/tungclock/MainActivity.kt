package com.example.tungclock

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tungclock.ui.theme.TungClockTheme
import kotlinx.coroutines.delay
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.ZonedDateTime.now
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TungClockTheme {

                var tela by remember { mutableStateOf(0) }

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        when (tela) {
                            0 -> Relogio()
                            1 -> Cronometro()
                            2 -> Timer()
                        }
                    }

                    Row(
                        modifier = Modifier
                            .background(Color(199, 199, 199, 91))
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Button(
                            onClick = { tela = 0 },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xB44CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Relógio")
                        }

                        Button(
                            onClick = { tela = 1 },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xAE4CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Cronômetro")
                        }

                        Button(
                            onClick = { tela = 2 },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xA44CAF50),
                                contentColor = Color.White
                            )
                        ) {
                            Text("Timer")
                        }
                    }
                }
            }
        }
    }

}

// --------------------------------------------------
// RELÓGIO + ALARME
// --------------------------------------------------

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Relogio() {

    val fusos = listOf(
        "São Paulo" to "America/Sao_Paulo",
        "Nova York" to "America/New_York",
        "Los Angeles" to "America/Los_Angeles",
        "Londres" to "Europe/London",
        "Paris" to "Europe/Paris",
        "Tóquio" to "Asia/Tokyo"
    )

    var fuso by remember { mutableStateOf(fusos[0]) }

    var hora by remember {
        mutableStateOf(
            now(ZoneId.of(fusos[0].second))
        )
    }

    var horaAlarme by remember { mutableStateOf("") }
    var minutoAlarme by remember { mutableStateOf("") }
    var alarmeAtivo by remember { mutableStateOf(false) }

    val context = LocalContext.current

// --------------------------------------------------
// ATUALIZA O RELÓGIO
// --------------------------------------------------

    LaunchedEffect(fuso) {

        while (true) {

            hora = now(
                ZoneId.of(fuso.second)
            )

            delay(1000)
        }
    }

// --------------------------------------------------
// VERIFICA O ALARME
// --------------------------------------------------

    LaunchedEffect(alarmeAtivo) {

        while (alarmeAtivo) {

            val agora = now()

            val horaDigitada = horaAlarme.toIntOrNull()
            val minutoDigitado = minutoAlarme.toIntOrNull()

            if (
                horaDigitada != null &&
                minutoDigitado != null &&
                horaDigitada == agora.hour &&
                minutoDigitado == agora.minute
            ) {

                Toast.makeText(
                    context,
                    "⏰ Alarme!",
                    Toast.LENGTH_LONG
                ).show()

                alarmeAtivo = false
            }

            delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .background(Color(143, 153, 162, 60))
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "CLOCK.IO",
            fontSize = 30.sp
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        Text(
            text = hora.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
            ),
            fontSize = 24.sp
        )

        Text(
            text = hora.format(
                DateTimeFormatter.ofPattern("HH:mm:ss")
            ),
            fontSize = 48.sp
        )

        Spacer(
            modifier = Modifier.height(15.dp)
        )

        // --------------------------------------------------
        // ESCOLHER FUSO
        // --------------------------------------------------

        var menuAberto by remember {
            mutableStateOf(false)
        }

        Box {

            Button(
                onClick = {
                    menuAberto = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0x667A7A7A),
                    contentColor = Color.White
                )
            ) {
                Text(fuso.first)
            }

            DropdownMenu(
                expanded = menuAberto,
                onDismissRequest = {
                    menuAberto = false
                }
            ) {

                fusos.forEach { fusoItem ->

                    DropdownMenuItem(
                        text = {
                            Text(fusoItem.first)
                        },
                        onClick = {

                            fuso = fusoItem
                            menuAberto = false
                        }
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // --------------------------------------------------
        // ALARME
        // --------------------------------------------------

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(
                    RoundedCornerShape(16.dp)
                )
                .background(
                    Color(0x667A7A7A)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "⏰ ALARME",
                fontSize = 26.sp,
                color = Color.White
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                TextField(
                    value = horaAlarme,
                    onValueChange = {

                        horaAlarme = it
                            .filter { c -> c.isDigit() }
                            .take(2)
                    },
                    label = {
                        Text("Hora")
                    },
                    enabled = !alarmeAtivo,
                    modifier = Modifier.width(90.dp)
                )

                Text(
                    text = " : ",
                    fontSize = 25.sp,
                    color = Color.White
                )

                TextField(
                    value = minutoAlarme,
                    onValueChange = {

                        minutoAlarme = it
                            .filter { c -> c.isDigit() }
                            .take(2)
                    },
                    label = {
                        Text("Min")
                    },
                    enabled = !alarmeAtivo,
                    modifier = Modifier.width(90.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row {

                Button(
                    onClick = {

                        val h = horaAlarme.toIntOrNull()
                        val m = minutoAlarme.toIntOrNull()

                        if (
                            h != null &&
                            m != null &&
                            h in 0..23 &&
                            m in 0..59
                        ) {

                            alarmeAtivo = true

                            Toast.makeText(
                                context,
                                "Alarme definido para %02d:%02d"
                                    .format(h, m),
                                Toast.LENGTH_SHORT
                            ).show()

                        } else {

                            Toast.makeText(
                                context,
                                "Digite um horário válido",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF22D32A),
                        contentColor = Color.White
                    ),
                    enabled = !alarmeAtivo
                ) {

                    Text("Ativar")
                }

                Spacer(
                    modifier = Modifier.width(10.dp)
                )

                Button(
                    onClick = {

                        alarmeAtivo = false

                        Toast.makeText(
                            context,
                            "Alarme desativado",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFAF4C5B),
                        contentColor = Color.White
                    )
                ) {

                    Text("Desativar")
                }
            }

            if (alarmeAtivo) {

                Text(
                    text = "🔔 Alarme ativado",
                    color = Color.Green,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }

}

// --------------------------------------------------
// CRONÔMETRO
// --------------------------------------------------

@Composable
fun Cronometro() {

    var segundos by remember {
        mutableStateOf(0)
    }

    var rodando by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(rodando) {

        while (rodando) {

            delay(1000)

            segundos++
        }
    }

    val horas = segundos / 3600
    val minutos = (segundos % 3600) / 60
    val seg = segundos % 60

    Column(
        modifier = Modifier
            .background(
                Color(199, 199, 199, 91)
            )
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "CRONÔMETRO",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Text(
            text = "%02d:%02d:%02d"
                .format(horas, minutos, seg),
            fontSize = 50.sp
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Row {

            Button(
                onClick = {
                    rodando = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF22D32A),
                    contentColor = Color.White
                )
            ) {

                Text("Iniciar")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = {
                    rodando = false
                }
            ) {

                Text("Pausar")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = {

                    rodando = false
                    segundos = 0
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFAF4C5B),
                    contentColor = Color.White
                )
            ) {

                Text("Zerar")
            }
        }
    }

}

// --------------------------------------------------
// TIMER
// --------------------------------------------------

@Composable
fun Timer() {

    var minutosInput by remember {
        mutableStateOf("")
    }

    var segundos by remember {
        mutableStateOf(0)
    }

    var rodando by remember {
        mutableStateOf(false)
    }

// --------------------------------------------------
// CONTAGEM REGRESSIVA
// --------------------------------------------------

    LaunchedEffect(rodando) {

        while (rodando && segundos > 0) {

            delay(1000)

            segundos--
        }

        if (segundos == 0) {

            rodando = false
        }
    }

    Column(
        modifier = Modifier
            .background(
                Color(143, 153, 162, 60)
            )
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "TIMER",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        Text(
            text = "%02d:%02d".format(
                segundos / 60,
                segundos % 60
            ),
            fontSize = 55.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        TextField(
            value = minutosInput,
            onValueChange = {

                minutosInput = it
                    .filter { c -> c.isDigit() }
            },
            label = {
                Text("Minutos")
            },
            enabled = !rodando
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Row {

            Button(
                onClick = {

                    val minutos =
                        minutosInput.toIntOrNull() ?: 0

                    if (minutos > 0) {

                        segundos = minutos * 60
                        rodando = true
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF22D32A),
                    contentColor = Color.White
                ),
                enabled = !rodando
            ) {

                Text("Iniciar")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = {
                    rodando = false
                },
                enabled = rodando
            ) {

                Text("Pausar")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = {

                    rodando = false
                    segundos = 0
                    minutosInput = ""
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFAF4C5B),
                    contentColor = Color.White
                )
            ) {

                Text("Zerar")
            }
        }

        if (
            !rodando &&
            segundos == 0 &&
            minutosInput.isNotEmpty()
        ) {

            Text(
                text = "⏰ TEMPO ESGOTADO!",
                fontSize = 20.sp,
                modifier = Modifier.padding(20.dp)
            )
        }
    }

}