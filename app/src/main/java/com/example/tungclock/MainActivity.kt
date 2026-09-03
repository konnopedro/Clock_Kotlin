package com.example.tungclock

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

private val Fundo = Color(0xFF0B0E17)
private val CardFundo = Color(0xFF171D2B)
private val Roxo = Color(0xFF9B7CFF)
private val Azul = Color(0xFF42B9F2)
private val Texto = Color.White
private val TextoSecundario = Color(0xFF9CA3B5)

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                TunTunClock()
            }
        }
    }
}

// ==================== APLICATIVO ====================

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TunTunClock() {

    var telaAtual by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = Fundo,

        bottomBar = {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Fundo)
                    .padding(8.dp),

                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                TextButton(
                    onClick = {
                        telaAtual = 0
                    }
                ) {
                    Text(
                        text = "◉ Relógio",
                        color = if (telaAtual == 0)
                            Roxo
                        else
                            TextoSecundario
                    )
                }

                TextButton(
                    onClick = {
                        telaAtual = 1
                    }
                ) {
                    Text(
                        text = "◉ Crono",
                        color = if (telaAtual == 1)
                            Roxo
                        else
                            TextoSecundario
                    )
                }

                TextButton(
                    onClick = {
                        telaAtual = 2
                    }
                ) {
                    Text(
                        text = "◉ Timer",
                        color = if (telaAtual == 2)
                            Roxo
                        else
                            TextoSecundario
                    )
                }
            }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {

            when (telaAtual) {

                0 -> Relogio()

                1 -> Crono()

                2 -> Timer()
            }
        }
    }
}

// ==================== RELÓGIO ====================

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Relogio() {

    var horaAtual by remember {
        mutableStateOf(
            java.time.ZonedDateTime.now(
                java.time.ZoneId.of("America/Sao_Paulo")
            )
        )
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000)

            horaAtual = java.time.ZonedDateTime.now(
                java.time.ZoneId.of("America/Sao_Paulo")
            )
        }
    }

    val hora = horaAtual.format(
        java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Composable próprio 1
        RelogioCabecalho()

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = hora,
            color = Texto,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "São Paulo, Brasil",
            color = TextoSecundario,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Composable próprio 2
        ProximoAlarme()

        Spacer(modifier = Modifier.height(28.dp))

        // Composable próprio 3
        FusoHorario()
    }
}

@Composable
fun RelogioCabecalho() {

    Text(
        text = "TUN TUN CLOCK",
        color = Roxo,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(12.dp))

    Text(
        text = "Bom dia, meu docinho",
        color = Texto,
        fontSize = 15.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Quinta-feira, 28 de agosto",
        color = TextoSecundario,
        fontSize = 13.sp
    )
}

@Composable
fun ProximoAlarme() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardFundo)
                .padding(16.dp),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "PRÓXIMO ALARME",
                    color = Roxo,
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "07:00",
                    color = Texto,
                    fontSize = 14.sp
                )

                Text(
                    text = "Amanhã",
                    color = TextoSecundario,
                    fontSize = 12.sp
                )
            }

            // Button adicionado para cumprir o requisito
            Button(
                onClick = {
                    // Ação futura para adicionar alarme
                }
            ) {
                Text(
                    text = "+"
                )
            }
        }
    }
}

@Composable
fun FusoHorario() {

    Text(
        text = "SEU FUSO HORÁRIO",
        color = Texto,
        fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(10.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardFundo)
                .padding(16.dp)
        ) {

            Text(
                text = "Curitiba",
                color = Texto,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "BRT · UTC−3",
                color = TextoSecundario,
                fontSize = 12.sp
            )
        }
    }
}

// ==================== CRONÔMETRO ====================

@Composable
fun Crono() {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Composable próprio 1
        CabecalhoCrono(
            rodando = rodando
        )

        Spacer(modifier = Modifier.height(35.dp))

        // Composable próprio 2
        CronometroCircular(
            segundos = segundos,
            rodando = rodando
        )

        Spacer(modifier = Modifier.height(28.dp))

        BotoesCrono(
            rodando = rodando,
            onReset = {
                rodando = false
                segundos = 0
            },
            onToggle = {
                rodando = !rodando
            }
        )

        Spacer(modifier = Modifier.height(35.dp))

        // Composable próprio 3
        UltimaVolta(
            segundos = segundos
        )
    }
}

@Composable
fun CabecalhoCrono(
    rodando: Boolean
) {

    Text(
        text = if (rodando)
            "TUN TUN CLOCK · EM ANDAMENTO"
        else
            "TUN TUN CLOCK · PRECISÃO",

        color = Roxo,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "Cronômetro",
        color = Texto,
        fontSize = 15.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = if (rodando)
            "Mantenha o foco"
        else
            "Pronto para começar",

        color = TextoSecundario,
        fontSize = 13.sp
    )
}

@Composable
fun CronometroCircular(
    segundos: Int,
    rodando: Boolean
) {

    Box(
        modifier = Modifier
            .size(210.dp)
            .background(
                color = CardFundo,
                shape = CircleShape
            ),

        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "%02d:%02d:%02d".format(
                    segundos / 3600,
                    (segundos % 3600) / 60,
                    segundos % 60
                ),

                color = Texto,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (rodando)
                    "em execução"
                else
                    "sem voltas",

                color = TextoSecundario,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun BotoesCrono(
    rodando: Boolean,
    onReset: () -> Unit,
    onToggle: () -> Unit
) {

    Row(
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onReset
        ) {

            Text(
                text = "VOLTAR"
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Button(
            onClick = onToggle
        ) {

            Text(
                text = if (rodando)
                    "PAUSAR"
                else
                    "▶ INICIAR"
            )
        }
    }
}

@Composable
fun UltimaVolta(
    segundos: Int
) {

    Text(
        text = "ÚLTIMA VOLTA",
        color = Texto,
        fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(10.dp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardFundo)
                .padding(16.dp),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Volta 1",
                color = TextoSecundario,
                fontSize = 12.sp
            )

            Text(
                text = "%02d:%02d".format(
                    segundos / 60,
                    segundos % 60
                ),

                color = Texto,
                fontSize = 12.sp
            )
        }
    }
}

// ==================== TIMER ====================

@Composable
fun Timer() {

    var minutos by remember {
        mutableStateOf("15")
    }

    var segundos by remember {
        mutableStateOf(0)
    }

    var rodando by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(rodando) {

        while (rodando) {

            delay(1000)

            if (minutos.toIntOrNull() == 0 && segundos == 0) {

                rodando = false

            } else if (segundos > 0) {

                segundos--

            } else {

                minutos = (
                        (minutos.toIntOrNull() ?: 1) - 1
                        ).toString()

                segundos = 59
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Composable próprio 1
        CabecalhoTimer()

        Spacer(modifier = Modifier.height(25.dp))

        // Composable próprio 2
        DisplayTimer(
            minutos = minutos,
            segundos = segundos
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Composable próprio 3
        AtalhosTimer(
            onFiveMinutes = {
                minutos = "5"
                segundos = 0
            },
            onTenMinutes = {
                minutos = "10"
                segundos = 0
            },
            onFifteenMinutes = {
                minutos = "15"
                segundos = 0
            }
        )

        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = minutos,

            onValueChange = {
                minutos = it
            },

            label = {
                Text("Minutos")
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        BotaoTimer(
            rodando = rodando,
            onToggle = {
                rodando = !rodando
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "O timer tocará quando chegar a zero.",
            color = TextoSecundario,
            fontSize = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CabecalhoTimer() {

    Text(
        text = "TUN TUN CLOCK · TIMER",
        color = Roxo,
        fontSize = 14.sp
    )

    Spacer(modifier = Modifier.height(14.dp))

    Text(
        text = "Timer",
        color = Texto,
        fontSize = 15.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    Text(
        text = "Escolha uma duração",
        color = TextoSecundario,
        fontSize = 13.sp
    )
}

@Composable
fun DisplayTimer(
    minutos: String,
    segundos: Int
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CardFundo)
                .padding(25.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "%02d:%02d:00".format(
                    minutos.toIntOrNull() ?: 0,
                    segundos
                ),

                color = Texto,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "horas · minutos · segundos",
                color = TextoSecundario,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun AtalhosTimer(
    onFiveMinutes: () -> Unit,
    onTenMinutes: () -> Unit,
    onFifteenMinutes: () -> Unit
) {

    Text(
        text = "ATALHOS",
        color = Texto,
        fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onFiveMinutes
        ) {
            Text("5 MIN")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onTenMinutes
        ) {
            Text("10 MIN")
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = onFifteenMinutes
        ) {
            Text("15 MIN")
        }
    }
}

@Composable
fun BotaoTimer(
    rodando: Boolean,
    onToggle: () -> Unit
) {

    val context = LocalContext.current

    Button(
        onClick = {

            onToggle()

            Toast.makeText(
                context,
                if (!rodando)
                    "Timer iniciado!"
                else
                    "Timer pausado!",
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {

        Text(
            text = if (rodando)
                "PAUSAR"
            else
                "▶ INICIAR"
        )
    }
}
